package com.example.icarealot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icarealot.R;
import com.example.icarealot.model.Ongs;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OngsAdapter extends RecyclerView.Adapter<OngsAdapter.OngsViewHolder> {

  LayoutInflater inflater;
  List<Ongs> ongsList;

  public OngsAdapter(Context context, List<Ongs> ongsList) {
    this.inflater = LayoutInflater.from(context);
    this.ongsList = ongsList;
  }

  @NonNull
  @Override
  public OngsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.testes, parent, false);
    return new OngsViewHolder(view);
  }

  public class OngsViewHolder extends RecyclerView.ViewHolder {
    TextView id, nome, descricao, telefone;
    ImageView ongCoverImage;
    public OngsViewHolder(@NonNull View itemView) {
      super(itemView);
      //id = itemView.findViewById(R.id.id);
      nome = itemView.findViewById(R.id.ongsNome);
      //descricao = itemView.findViewById(R.id.descricao);
      //telefone = itemView.findViewById(R.id.telefone);
      ongCoverImage = itemView.findViewById(R.id.ongsFoto);
    }
  }



  @Override
  public void onBindViewHolder(@NonNull OngsViewHolder holder, int position) {
    //holder.id.setText(ongsList.get(position).getId());
    holder.nome.setText(ongsList.get(position).getNome());
    //holder.descricao.setText(ongsList.get(position).getDescricao());
    //holder.telefone.setText(ongsList.get(position).getTelefone());
    Picasso.get().load(ongsList.get(position).getFoto()).into(holder.ongCoverImage);
  }

  @Override
  public int getItemCount() {
    return this.ongsList.size();
  }
}
