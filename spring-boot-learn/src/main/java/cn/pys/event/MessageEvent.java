package cn.pys.event;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@ToString
public class MessageEvent extends ApplicationEvent {
    private Long postId;
    private Long fromUserId;
    private Long toUserId;
    private int event;
    public MessageEvent(Object source) {
        super(source);
    }
}
