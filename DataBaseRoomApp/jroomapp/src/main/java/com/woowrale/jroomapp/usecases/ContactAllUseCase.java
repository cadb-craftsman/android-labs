package com.woowrale.jroomapp.usecases;

import com.woowrale.jroomapp.data.repository.LocalRepository;
import com.woowrale.jroomapp.domain.model.Contact;
import com.woowrale.jroomapp.usecases.base.BaseUseCase;
import com.woowrale.jroomapp.usecases.threads.JobScheduler;
import com.woowrale.jroomapp.usecases.threads.UIScheduler;

import java.util.List;

import io.reactivex.Single;

public class ContactAllUseCase extends BaseUseCase<List<Contact>, ContactAllUseCase.Params> {

    private LocalRepository localRepository;

    public ContactAllUseCase(LocalRepository localRepository, UIScheduler uiScheduler, JobScheduler jobScheduler) {
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

