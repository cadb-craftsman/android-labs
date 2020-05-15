package com.woowrale.jcleanarchitecture.di.factory;

import com.woowrale.data.repository.remote.RemoteRepository;
import com.woowrale.usecase.base.BaseUseCase;
import com.woowrale.usecase.threads.JobScheduler;
import com.woowrale.usecase.threads.UIScheduler;
import com.woowrale.usecase.usescases.GetContactsUseCase;

import javax.inject.Inject;

public class UseCaseFactory {

    private RemoteRepository repository;
    private UIScheduler uiScheduler;
    private JobScheduler jobScheduler;

    @Inject
    public UseCaseFactory(RemoteRepository repository,
                          UIScheduler uiScheduler,
                          JobScheduler jobScheduler){
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    public BaseUseCase getContacts(){
        return new GetContactsUseCase(repository, uiScheduler, jobScheduler);
    }
}
