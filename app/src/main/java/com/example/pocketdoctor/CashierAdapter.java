package com.example.pocketdoctor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CashierAdapter extends RecyclerView.Adapter {
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;
    private Context context;
    ArrayList<User> arrayList;
    Button button;
    boolean clicked = false;

    public CashierAdapter(Context context, ArrayList<User> arrayList)
    {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.arrayList = arrayList;
    }

    public long getItemId(int position){
        return position;
    }

    public Object getItem(int position){
        return arrayList.get(position);
    }


    void setClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_cashier_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = arrayList.get(position);
        ((ViewHolder)holder).fullName.setText(user.getFirstName() + " " + user.getLastName());
        ((ViewHolder)holder).duePaymentMessage.setText("pending payment " + user.duePaymentMessage);
        button = ((ViewHolder) holder).sendReminder;
        button.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView fullName;
        TextView duePaymentMessage;
        Button sendReminder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.cashierViewFullName);
            duePaymentMessage = itemView.findViewById(R.id.paymentMessage);
            sendReminder = itemView.findViewById(R.id.buttonSendPaymentReminder);

            itemView.setOnClickListener(this);
            sendReminder.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
                if(sendReminder.getId() == v.getId())
                {
                    sendReminder.setText("Payment reminder sent");
                    sendReminder.setBackgroundResource(R.drawable.rectangle_round_button_fill);
                    sendReminder.setTextColor(Color.parseColor("#FFFFFF"));
                    sendReminder.setCompoundDrawablesRelative(null,null,null,null);
                }
            }
    }

    //interface
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
