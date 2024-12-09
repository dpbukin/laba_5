package com.example.lab_5.L5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_5.databinding.ItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val characters = mutableListOf<Character>()

    fun submitList(newCharacters: List<Character>) {
        characters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.characterId.text = character.id.toString()
            binding.characterName.text = character.name
            Picasso.get().load(character.image).into(binding.characterImage)
            binding.characterStatus.text = character.status
            binding.characterSpecies.text = character.species
            binding.characterGender.text = character.gender
            binding.characterOrigin.text = character.origin.name
            binding.characterLocation.text = character.location.name
        }
    }
}

