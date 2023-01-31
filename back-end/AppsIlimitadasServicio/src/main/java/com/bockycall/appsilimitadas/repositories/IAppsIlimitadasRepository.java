package com.bockycall.appsilimitadas.repositories;

import com.bockycall.appsilimitadas.entities.AppsIlimitadas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAppsIlimitadasRepository extends MongoRepository<AppsIlimitadas,String> {

}
