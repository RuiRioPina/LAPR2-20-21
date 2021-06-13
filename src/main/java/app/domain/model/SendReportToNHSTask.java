package app.domain.model;

import java.util.TimerTask;
import com.nhs.report.Report2NHS;
public class SendReportToNHSTask extends TimerTask {

    Report2NHS report2NHS = new Report2NHS();

    public SendReportToNHSTask() {

    }

    @Override
    public void run() {
        Report2NHS.writeUsingFileWriter("yau");
    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

    @Override
    public long scheduledExecutionTime() {
        return super.scheduledExecutionTime();
    }
}
