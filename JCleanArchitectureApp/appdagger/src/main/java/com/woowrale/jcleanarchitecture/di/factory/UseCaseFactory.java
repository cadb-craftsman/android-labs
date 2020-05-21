package com.woowrale.jcleanarchitecture.di.factory;

import com.woowrale.data.repository.local.LocalRepository;
import com.woowrale.data.repository.remote.RemoteRepository;
import com.woowrale.usecase.base.BaseUseCase;
import com.woowrale.usecase.threads.JobScheduler;
import com.woowrale.usecase.threads.UIScheduler;
import com.woowrale.usecase.usescases.GetContactAllUseCase;
import com.woowrale.usecase.usescases.GetContactsUseCase;

import javax.inject.Inject;

public class UseCaseFactory {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;
    private UIScheduler uiScheduler;
    private JobScheduler jobScheduler;

    @Inject
    public UseCaseFactory(RemoteRepository remoteRepository,
                          LocalRepository localRepository,
                          UIScheduler uiScheduler,
                          JobScheduler jobScheduler){

        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    public BaseUseCase getRemoteContacts(){
        return new GetContactsUseCase(remoteRepository, uiScheduler, jobScheduler);
    }

    public BaseUseCase getLocalContacts(){
        return new GetContactAllUseCase(localRepository, uiScheduler, jobScheduler);
    }
}
