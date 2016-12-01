package first.web;

import java.util.Calendar;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import first.domain.CalendarUser;
import first.domain.Event;
import first.service.CalendarService;
import first.service.UserContext;
import first.web.model.CreateEventForm;

@Controller
@RequestMapping("/events")
public class EventsController {

	private static final Logger logger = Logger.getLogger(EventsController.class);

	private final CalendarService calendarService;
	private final UserContext userContext;

	@Autowired
	public EventsController(CalendarService calendarService, UserContext userContext) {
		logger.info("•••  criando EventsController");
		this.calendarService = calendarService;
		this.userContext = userContext;
	}

	@RequestMapping("/")
	public ModelAndView events() {
		logger.info("•••  events() running");
		return new ModelAndView("events/list", "events", calendarService.getEvents());
	}

	@RequestMapping("/my")
	public ModelAndView myEvents() {
		logger.info("•••  myEvents() running");
		CalendarUser currentUser = userContext.getCurrentUser();
		Integer currentUserId = currentUser.getId();
		ModelAndView result = new ModelAndView("events/my", "events", calendarService.findForUser(currentUserId));
		result.addObject("currentUser", currentUser);
		return result;
	}

	@RequestMapping("/{eventId}")
	public ModelAndView show(@PathVariable int eventId) {
		logger.info("•••  show() running");
		Event event = calendarService.getEvent(eventId);
		return new ModelAndView("events/show", "event", event);
	}

	@RequestMapping("/form")
	public String createEventForm(@ModelAttribute CreateEventForm createEventForm) {
		logger.info("•••  createEventForm() running");
		return "events/create";
	}

	/**
	 * Populates the form for creating an event with valid information. Useful
	 * so that users do not have to think when filling out the form for testing.
	 *
	 * @param createEventForm
	 * @return
	 */
	@RequestMapping(value = "/new", params = "auto")
	public String createEventFormAutoPopulate(@ModelAttribute CreateEventForm createEventForm) {
		logger.info("•••  createEventFormAutoPopulate() running");
		// provide default values to make user submission easier
		createEventForm.setSummary("A new event....");
		createEventForm.setDescription("This was autopopulated to save time creating a valid event.");
		createEventForm.setWhen(Calendar.getInstance());

		// make the attendee not the current user
		CalendarUser currentUser = userContext.getCurrentUser();
		int attendeeId = currentUser.getId() == 0 ? 1 : 0;
		CalendarUser attendee = calendarService.getUser(attendeeId);
		createEventForm.setAttendeeEmail(attendee.getEmail());

		return "events/create";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createEvent(@Valid CreateEventForm createEventForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("•••  createEvent running");
		if (result.hasErrors()) {
			return "events/create";
		}
		CalendarUser attendee = calendarService.findUserByEmail(createEventForm.getAttendeeEmail());
		if (attendee == null) {
			result.rejectValue("attendeeEmail", "attendeeEmail.missing",
					"Could not find a user for the provided Attendee Email");
		}
		if (result.hasErrors()) {
			return "events/create";
		}
		Event event = new Event();
		event.setAttendee(attendee);
		event.setDescription(createEventForm.getDescription());
		event.setOwner(userContext.getCurrentUser());
		event.setSummary(createEventForm.getSummary());
		event.setWhen(createEventForm.getWhen());
		calendarService.createEvent(event);
		redirectAttributes.addFlashAttribute("message", "Successfully added the new event");
		return "redirect:/events/my";
	}
}