package it.incalza.notification.bucket;

import it.incalza.notification.bucket.adapter.systemA.ANotificationDefaultReceiver;
import it.incalza.notification.bucket.adapter.systemA.model.ANotification;
import it.incalza.notification.bucket.adapter.systemB.BNotificationDefaultReceiver;
import it.incalza.notification.bucket.adapter.systemB.model.BNotification;
import it.incalza.notification.bucket.domain.DuplicateNotificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/receiver")
public class ReceiverResource {

    private final ANotificationDefaultReceiver aNotificationDefaultReceiver;
    private final BNotificationDefaultReceiver bNotificationDefaultReceiver;

    public ReceiverResource(ANotificationDefaultReceiver aNotificationDefaultReceiver, BNotificationDefaultReceiver bNotificationDefaultReceiver) {
        this.aNotificationDefaultReceiver = aNotificationDefaultReceiver;
        this.bNotificationDefaultReceiver = bNotificationDefaultReceiver;
    }

    @RequestMapping(path = "/systemA", method = {PUT, POST})
    public void receiverSystemA(@RequestBody Set<ANotification> aNotifications) {
        aNotificationDefaultReceiver.onReceive(aNotifications);
    }

    @RequestMapping(path = "/systemB", method = {PUT, POST})
    public void receiverSystemB(@RequestBody Set<BNotification> bNotifications) {
        bNotificationDefaultReceiver.onReceive(bNotifications);
    }




    @ExceptionHandler(DuplicateNotificationException.class)
    public void handleNotFoundException(Throwable e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }


}
