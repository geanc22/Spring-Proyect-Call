package com.bockycall.appsilimitadas.repositories;

import com.bockycall.appsilimitadas.entities.App;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAppsRepository extends MongoRepository<App,String> {
    App findAppByName(String name);

}
