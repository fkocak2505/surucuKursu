package com.bakiyem.surucu.project.activity.denemeSinavi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bakiyem.surucu.project.R;
import com.bakiyem.surucu.project.model.denemeSinavi.AnswerModel;

import java.util.List;

public class DenemeSinaviQuizAnswerAdapter extends RecyclerView.Adapter<DenemeSinaviQuizAnswerAdapter.ViewHolder> {

    private List<AnswerModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Boolean isShownAnswer;
    private Context context;

    // data is passed into the constructor
    DenemeSinaviQuizAnswerAdapter(Context context, List<AnswerModel> data, Boolean isShownAnswer) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.isShownAnswer = isShownAnswer;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_quiz_answer_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.questionNumber.setText(String.valueOf(mData.get(position).getQuestionNumber()));
        holder.answer.setText(mData.get(position).getQuestionAnswe());
        holder.cv_answer.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_circle_answer));

        if (isShownAnswer) {
            if (mData.get(position).isCorrectAnswe() != null) {
                if (mData.get(position).isCorrectAnswe()) {
                    holder.cv_answer.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_circle_correct_answer));
                    holder.answer.setTextColor(ContextCompat.getColor(context, R.color.white));
                } else {
                    holder.cv_answer.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_circle_wrong_answer));
                    holder.answer.setTextColor(ContextCompat.getColor(context, R.color.white));
                }
            } else {
                holder.cv_answer.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_circle_answer));
                holder.answer.setTextColor(ContextCompat.getColor(context, R.color.textColor));
            }
        } else{
            holder.cv_answer.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_circle_answer));
            holder.answer.setTextColor(ContextCompat.getColor(context, R.color.textColor));
        }



    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView answer;
        TextView questionNumber;
        CardView cv_answer;

        ViewHolder(View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.tv_answer);
            questionNumber = itemView.findViewById(R.id.tv_questionNumber);
            cv_answer = itemView.findViewById(R.id.cv_answer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(mData.get(getAdapterPosition()), getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    AnswerModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(AnswerModel view, int position);
    }
}