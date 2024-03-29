package mx.uniprotec.inicio.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

import mx.uniprotec.entidad.modelo.AsignacionModelo;

public class GoogleCalendar {

	public GoogleCalendar() {
		// TODO Auto-generated constructor stub
	}	private static final String APPLICATION_NAME = "control-uniprote1c.com";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/client_secret_560460290689-0ilr0g3mb231v1bhd5jqjdilntb4mgnb.apps.googleusercontent.com.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = GoogleCalendar.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8015).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    
    
	// Refer to the Java quickstart on how to setup the environment:
	// https://developers.google.com/calendar/quickstart/java
	// Change the scope to CalendarScopes.CALENDAR and delete any stored
	// credentials.
    public static void enviaCitaInstructor(AsignacionModelo asignacion) throws IOException, GeneralSecurityException {
    	// Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

    	Event event = new Event()
    		    .setSummary("Evento de Asignacion AAAA")
    		    .setLocation("Region 2 Queretaro")
    		    .setDescription("Ha confirmado la asignacion al curso AAA para cliente BBB con los siguientes datos de cita:");

    		DateTime startDateTime = new DateTime("2020-10-02T09:00:00-07:00");
    		EventDateTime start = new EventDateTime()
    		    .setDateTime(startDateTime)
    		    .setTimeZone("America/Mexico_City");
    		event.setStart(start);

    		DateTime endDateTime = new DateTime("2020-10-02T09:30:00-07:00");
    		EventDateTime end = new EventDateTime()
    		    .setDateTime(endDateTime)
    		    .setTimeZone("America/Mexico_City");
    		event.setEnd(end);

    		String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
    		event.setRecurrence(Arrays.asList(recurrence));

    		EventAttendee[] attendees = new EventAttendee[] {
    		    new EventAttendee().setEmail("kaltiaservicios@gmail.com")
//    		    new EventAttendee().setEmail("hrivas.cortes@gmail.com"),
    		};
    		event.setAttendees(Arrays.asList(attendees));

    		EventReminder[] reminderOverrides = new EventReminder[] {
    		    new EventReminder().setMethod("email").setMinutes(24 * 60),
    		    new EventReminder().setMethod("popup").setMinutes(10),
    		};
    		Event.Reminders reminders = new Event.Reminders()
    		    .setUseDefault(false)
    		    .setOverrides(Arrays.asList(reminderOverrides));
    		event.setReminders(reminders);

    		String calendarId = "Recordatorios";
    		event = service.events().insert(calendarId, event).execute();
    		System.out.printf("Event created: %s\n", event.getHtmlLink());


    }
	
}
