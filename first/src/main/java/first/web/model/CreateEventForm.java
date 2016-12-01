package first.web.model;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import first.domain.Event;
import first.service.CalendarService;
import first.service.UserContext;
import first.web.EventsController;

/**
 * A form object that is used for creating a new {@link Event}. Using a
 * different object is one way of preventing malicious users from filling out
 * field that they should not (i.e. fill out a different owner field).
 *
 * @author Rob Winch
 *
 */
public class CreateEventForm {

	private static final Logger logger = Logger.getLogger(CreateEventForm.class);

	public CreateEventForm() {
		logger.info("•••  criando CreateEventForm");
	}

	@NotEmpty(message = "Attendee Email is required")
	@Email(message = "Attendee Email must be a valid email")
	private String attendeeEmail;
	@NotEmpty(message = "Summary is required")
	private String summary;
	@NotEmpty(message = "Description is required")
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@NotNull(message = "Event Date/Time is required")
	private Calendar when;

	public String getAttendeeEmail() {
		logger.info("•••  getAttendeeEmail() running");
		return attendeeEmail;
	}

	public void setAttendeeEmail(String attendeeEmail) {
		logger.info("•••  setAttendeeEmail() running");
		this.attendeeEmail = attendeeEmail;
	}

	public String getSummary() {
		logger.info("•••  getSummary() running");
		return summary;
	}

	public void setSummary(String summary) {
		logger.info("•••  setSummary() running");
		this.summary = summary;
	}

	public String getDescription() {
		logger.info("•••  getDescription() running");
		return description;
	}

	public void setDescription(String description) {
		logger.info("•••  setDescription() running");
		this.description = description;
	}

	public Calendar getWhen() {
		logger.info("•••  getWhen() running");
		return when;
	}

	public void setWhen(Calendar when) {
		logger.info("•••  setWhen() running");
		this.when = when;
	}
}