package s2m.tryviperarchitecture.firstusecase.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import s2m.tryviperarchitecture.R;
import s2m.tryviperarchitecture.firstusecase.interactor.Comment;

/**
 * Created by cta on 14/09/15.
 */
public class CommentsArrayAdapter extends ArrayAdapter<Comment>
{
    public CommentsArrayAdapter(Context context, List<Comment> items)
    {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Comment comment = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_row_item, parent, false);
        }

        // TODO not yet a view holder here
        TextView commentIdTextView = (TextView) convertView.findViewById(R.id.commentId);
        TextView commentValueTextView = (TextView) convertView.findViewById(R.id.commentValue);

        commentIdTextView.setText(String.valueOf(comment.getCommentId()));
        commentValueTextView.setText(comment.getCommentValue());

        return convertView;
    }
}
