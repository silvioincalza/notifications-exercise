

## Architecture Diagram

This reference application is based on Hexagonal architecture.

![hight-level-diagram](https://raw.githubusercontent.com/silvioincalza/notifications-exercise/master/docs/hight-level-diagram.png)

Sequence diagram of use cases:
- Receiver a notification:

![use_case_1](https://raw.githubusercontent.com/silvioincalza/notifications-exercise/master/docs/sq_usecase_1.png)

- Notify the notifications to user

![use_case_2](https://raw.githubusercontent.com/silvioincalza/notifications-exercise/master/docs/sq_usecase_2.png)


Objects Diagram:

![diagram](https://raw.githubusercontent.com/silvioincalza/notifications-exercise/master/docs/diagram.png)


#### Mysql or PostgreSQL? 
For this prototype I choose for demo version a H2 embedded database, the functions of this database are equal to  Mysql or PostgreSQL more o less.
I'm not a DBA Administrator so I don't know all peculiarities of these databases.
Any way, if I have to choose one of these databases, I think that when reliability and data integrity are an absolute necessity without excuses, PostgreSQL is the better choice.

#### How to extends with another notification system
Create a DTO of notification contract and this need to implement an interface: it.incalza.notification.bucket.domain.Notification.

The unique constraint of contract is to have the user id , this because we need to retrieve the email of user.

The uuid is used only to have a internal ID of each notification we receive.

```java

public class ANotification implements Notification {
    
    private String userId;    
    ...
    
    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public UUID getUuid() {
         return randomUUID();
    }
    
    ...
}

```

In order to persist the notification received we need to implement a repository with interface it.incalza.notification.bucket.domain.NotificationRepository


```java

public class ANotificationRepository implements NotificationRepository {
    
   public save(Set<ANotification> notifications0) {
       ....
   }
    
}

```
End at the last instance a default receiver use case

```java

public class ANotificationDefaultReceiver extends DefaultNotificationReceiver<ANotification> {

    public ANotificationDefaultReceiver(ANotificationRepository notificationRepository, BucketRepository bucketRepository, UserRepository userRepository) {
        super(notificationRepository, bucketRepository, userRepository);
    }

    @Override
    public String systemId() {
        return ANotification.class.getSimpleName();
    }
}


```

#### How to solve the high load performance

On database layer I choose to persiste all data with only one insert query and not a insert query for each notification received

On Microservice layer I choose to create three nodes managed from a load balancer in round robin configured with apache with the ajp connector of the servlet container.

#### If you want to use a NoSql DB?

You can implement the repositories with a nosql integration

- NotificationRepository for each notification system configured
- UserRepository
- BucketRepository


####  How to build

```
mvn clean install -e
```

For start the app from maven
```
cd notification-bucket-endpoint-rest/
mvn spring-boot:run -e
```

##### System requirements:
Java 8


##### Tips & tricks
Performance of generate a random UUID http://stackoverflow.com/a/35050629/7159282
