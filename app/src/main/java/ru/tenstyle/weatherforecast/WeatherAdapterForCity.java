package ru.tenstyle.weatherforecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapterForCity extends RecyclerView.Adapter<WeatherAdapterForCity.ViewHolder> {
    private String[]dataSource;
    private OnItemClickListener itemClickListener;

    public WeatherAdapterForCity(String[] dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public WeatherAdapterForCity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_for_city_choose, parent, false);

        return new WeatherAdapterForCity.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(dataSource[position]);
    }


    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public interface OnItemClickListener{
        void onItemClick(View v);
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.itemClickListener = onItemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener != null){
                        itemClickListener.onItemClick(v);
                    }
                }
            });
        }

        public TextView getTextView() {
            return textView;
        }
    }
}