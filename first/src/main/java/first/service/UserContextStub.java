package first.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import first.dataaccess.CalendarUserDao;
import first.domain.CalendarUser;
import first.web.EventsController;

/**
 * Returns the same user for every call to {@link #getCurrentUser()}. This is
 * used prior to adding security, so that the rest of the application can be
 * used.
 *
 * @author Rob Winch
 */
@Component
public class UserContextStub implements UserContext {

	private static final Logger logger = Logger.getLogger(UserContextStub.class);

	private final CalendarUserDao userService;
	/**
	 * The {@link CalendarUser#getId()} for the user that is representing the
	 * currently logged in user. This can be modified using
	 * {@link #setCurrentUser(CalendarUser)}
	 */
	private int currentUserId = 0;

	@Autowired
	public UserContextStub(CalendarUserDao userService) {
		logger.info("•••  criando UserContextStub");
		if (userService == null) {
			throw new IllegalArgumentException("userService cannot be null");
		}
		this.userService = userService;
	}

	@Override
	public CalendarUser getCurrentUser() {
		return userService.getUser(currentUserId);
	}

	@Override
	public void setCurrentUser(CalendarUser user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		Integer currentId = user.getId();
		if (currentId == null) {
			throw new IllegalArgumentException("user.getId() cannot be null");
		}
		this.currentUserId = currentId;
	}
}