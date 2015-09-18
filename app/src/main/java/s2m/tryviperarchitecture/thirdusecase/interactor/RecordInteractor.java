package s2m.tryviperarchitecture.thirdusecase.interactor;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by cta on 17/09/15.
 */
public class RecordInteractor
{
    private static final String TAG = RecordInteractor.class.getSimpleName();

    private final static String RECORDED_FILE_NAME = "Recording.mp3";

    private MediaRecorder audioRecorder;

    private DataChangeListener dataChangeListener;

    private File output;

    public void setOutput(DataChangeListener dataChangeListener)
    {
        this.dataChangeListener = dataChangeListener;
    }

    public void initRecorder()
    {
        output = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), RECORDED_FILE_NAME);
    }

    public void releaseRecorder()
    {
        if (audioRecorder != null)
        {
            stopRecording();
        }
        audioRecorder = null;
    }

    public void startRecording()
    {
        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        audioRecorder.setOutputFile(output.getAbsolutePath());

        try
        {
            audioRecorder.prepare();
            audioRecorder.start();
        } catch (IllegalStateException | IOException e)
        {
            Log.e(TAG, e.getMessage(), e);
            dataChangeListener.exceptionFromInteractor();
        }

    }

    public void stopRecording()
    {
        try
        {
            audioRecorder.stop();
            audioRecorder.reset();
            audioRecorder.release();
            audioRecorder = null;
        }
        catch (IllegalStateException e)
        {
            Log.e(TAG, e.getMessage(), e);
            dataChangeListener.exceptionFromInteractor();
        }
    }
}
