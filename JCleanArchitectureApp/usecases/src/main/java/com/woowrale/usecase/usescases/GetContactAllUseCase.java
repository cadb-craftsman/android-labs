package com.woowrale.usecase.usescases;

import com.woowrale.data.repository.local.LocalRepository;
import com.woowrale.domain.model.Contact;
import com.woowrale.usecase.base.BaseUseCase;
import com.woowrale.usecase.threads.JobScheduler;
import com.woowrale.usecase.threads.UIScheduler;

import java.util.List;

import io.reactivex.Single;

public class GetContactAllUseCase extends BaseUseCase<List<Contact>, GetContactAllUseCase.Params> {

    private LocalRepository localRepository;

    public GetContactAllUseCase(LocalRepository localRepository, UIScheduler uiScheduler, JobScheduler jobScheduler) {
        super(uiScheduler, jobScheduler);
        this.localRepository = localRepository;
    }

    @Override
    protected Single<List<Contact>> buildUseCaseObservable(Params params) {
        return Single.create(emitter -> {
            try {
                saveAllContacstCase(params.getContacts());
                List<Contact> contacts = getAllCase();
                emitter.onSuccess(contacts);
            } catch (Exception exception) {
                if (!emitter.isDisposed()) {
                    emitter.onError(exception);
                }
            }
        });
    }

    public Contact findByIdCase(Integer id) {
        return localRepository.findById(id);
    }

    public List<Contact> getAllCase() {
        return localRepository.getAll();
    }

    public void saveAllContacstCase(List<Contact> contacts) {
        localRepository.saveAll(contacts);
    }

    public void updateContactCase(Contact contact) {
        localRepository.updateContact(contact);
    }

    public static final class Params {

        List<Contact> contacts;

        public Params(List<Contact> contacts) {
            this.contacts = contacts;
        }

        public List<Contact> getContacts() {
            return contacts;
        }
    }
}

