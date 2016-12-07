# notifications-exercise

[Go to the my solution](docs/README.md)

####Exercise

Design and implement a system that subscribes to notifications coming from two other external systems and shows the notifications in a common interface.

A single notification request can contains up to thousands of notification records.

The notifications received from system A are identified by:
```
userId: the user to which the notification is sent
message: the notification content
```

The notifications received from system B are identified by:
```
userId: the user to which the notification is sent
message: the notification content
media: the url of media content
mediaType: the type of media (picture, video)
```

Only for system A: if the system fails to save a notification from the same request then neither one of them is saved.

As soon as a new notification comes in, the system must alert the user by e-mail informing him that he received the new notification. if the user has multiple unread notifications then the user must receive the e-mail only once.

In the future the system will integrate with other Notification Systems for which we don't know the format now.

#####Implementation Constraints:

You must use a Relational Database between MySQL or PostreSQL. Please argument your choice between them.

####In Designing the system please provide the following:

- A block diagram of the system that shows the main components and how they interact with each other
- Describe how the system can be extended to support other types of notifications
- Describe how you can solve high load problems at the server and database level.
- Describe what other risks can be identified and how can he handle them.
- Is a NoSql database more suited for the system?

