package com.woowrale.usecase.usescases;

import com.woowrale.data.repository.remote.RemoteRepository;
import com.woowrale.domain.model.Contact;
import com.woowrale.usecase.base.BaseUseCase;
import com.woowrale.usecase.threads.JobScheduler;
import com.woowrale.usecase.threads.UIScheduler;

import java.util.List;

import io.reactivex.Single;

public class GetContactsUseCase extends BaseUseCase<List<Contact>, GetContactsUseCase.Params> {

    private RemoteRepository remoteRepository;

    public GetContactsUseCase(RemoteRepository remoteRepository, UIScheduler uiScheduler, JobScheduler jobScheduler){
        super(uiScheduler, jobScheduler);
        this.remoteRepository = remoteRepository;
    }

    @Override
    protected Single<List<Contact>> buildUseCaseObservable(final Params params) {
        return Single.create(emitter -> {
            try {
                List<Contact> contacts = remoteRepository.getContacts(params.getApiContacts(), params.getSource(), params.getQuery());
                emitter.onSuccess(contacts);
            } catch (Exception exception){
                if (!emitter.isDisposed()) {
                    emitter.onError(exception);
                }
            }
        });
    }

    public static final class Params{

        private final String apiContacts;
        private final String source;
        private final String query;

        public Params(String apiContacts, String source, String query) {
            this.apiContacts = apiContacts;
            this.source = source;
            this.query = query;
        }

        public String getApiContacts() {
            return apiContacts;
        }

        public String getSource() {
            return source;
        }

        public String getQuery() {
            return query;
        }
    }
}
