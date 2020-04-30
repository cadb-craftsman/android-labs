package com.woowrale.jroomapp.di.factory;

import com.woowrale.jroomapp.data.repository.LocalRepository;
import com.woowrale.jroomapp.usecases.ContactAllUseCase;
import com.woowrale.jroomapp.usecases.base.BaseUseCase;
import com.woowrale.jroomapp.usecases.threads.JobScheduler;
import com.woowrale.jroomapp.usecases.threads.UIScheduler;

import javax.inject.Inject;

public class UseCaseFactory {

    private LocalRepository repository;
    private UIScheduler uiScheduler;
    private JobScheduler jobScheduler;

    @Inject
    public UseCaseFactory(LocalRepository repository,
                          UIScheduler uiScheduler,
                          JobScheduler jobScheduler){
        this.repository = repository;
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    public BaseUseCase getContacts(){
        return new ContactAllUseCase(repository, uiScheduler, jobScheduler);
    }
}
