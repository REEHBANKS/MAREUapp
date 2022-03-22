package di;

import com.banks.mareu.service.DummyMeetingApiService;
import com.banks.mareu.service.MeetingApiService;

public class DI {

    private static final MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService()
    {return service;}

}




