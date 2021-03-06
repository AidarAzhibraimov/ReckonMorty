package kg.geektech.reckonmortyapp.ui.characters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.reckonmortyapp.data.models.Result;
import kg.geektech.reckonmortyapp.databinding.ItemCharacterBinding;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder> {

    private List<Result> characters = new ArrayList<>();



    public void setCharacters(List<Result> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.onBind(characters.get(position));

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    protected class CharacterViewHolder extends RecyclerView.ViewHolder {

        private ItemCharacterBinding binding;
        public CharacterViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Result character) {
            binding.tvName.setText(character.getName());
            binding.tvStatus.setText(character.getStatus());
            Glide.with(binding.getRoot())
                    .load(character.getImage())
                    .centerCrop()
                    .into(binding.ivCharacter);
        }
    }
}
