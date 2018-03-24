package tk.service;

import tk.pojo.Tags;
import tk.pojo.Ticket;

/**
 * Created by tank on 2018/3/16.
 */
public interface IMsgEventService {

    void addTag(String tagName);

    Ticket createQrcode(String tagId);

    Tags getTags();

    String eventListener(String xmlText);
}
