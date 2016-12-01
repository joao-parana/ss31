package first.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import first.dataaccess.CalendarUserDao;
import first.dataaccess.EventDao;
import first.domain.CalendarUser;
import first.domain.Event;

/**
 * A default implementation of {@link CalendarService} that delegates to
 * {@link EventDao} and {@link CalendarUserDao}.
 *
 * @author Rob Winch
 *
 */
@Repository
public class DefaultCalendarService implements CalendarService {

	private static final Logger logger = Logger.getLogger(DefaultCalendarService.class);

	private final EventDao eventDao;
	private final CalendarUserDao userDao;

	@Autowired
	public DefaultCalendarService(EventDao eventDao, CalendarUserDao userDao) {
		logger.info("•••  criando DefaultCalendarService");
		if (eventDao == null) {
			throw new IllegalArgumentException("eventDao cannot be null");
		}
		if (userDao == null) {
			throw new IllegalArgumentException("userDao cannot be null");
		}
		this.eventDao = eventDao;
		this.userDao = userDao;
	}

	public Event getEvent(int eventId) {
		return eventDao.getEvent(eventId);
	}

	public int createEvent(Event event) {
		return eventDao.createEvent(event);
	}

	public List<Event> findForUser(int userId) {
		return eventDao.findForUser(userId);
	}

	public List<Event> getEvents() {
		return eventDao.getEvents();
	}

	public CalendarUser getUser(int id) {
		return userDao.getUser(id);
	}

	public CalendarUser findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	public List<CalendarUser> findUsersByEmail(String partialEmail) {
		return userDao.findUsersByEmail(partialEmail);
	}

	public int createUser(CalendarUser user) {
		return userDao.createUser(user);
	}
}