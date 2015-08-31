package name.walnut.mapper.core;

import java.util.List;
import java.util.Map;

import name.walnut.core.pojo.Notification;

public class NotificationSqlProvider {
	public String insert(Map<String, List<Notification>> parameter) {

		StringBuilder sb = new StringBuilder("INSERT INTO t_notification(message_id, user_id, is_read, notification_type) VALUES ");
		for(Notification notification : parameter.get("list")) {
			sb.append("(");
			sb.append(notification.getMessageId());
			sb.append(",");
			sb.append(notification.getUserId());
			sb.append(",");
			sb.append(notification.isRead());
			sb.append(",");
			sb.append(notification.getNotificationType().ordinal());
			sb.append(")");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
