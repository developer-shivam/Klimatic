package app.klimatic.ui.settings.presentation

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.klimatic.R
import app.klimatic.ui.utils.Theme
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_theme_item.view.ivThemeDisplay

class ThemeAdapter(
    private val themes: List<Theme>,
    private val action: (Theme) -> Unit
) : RecyclerView.Adapter<ThemeAdapter.ThemePaletteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemePaletteViewHolder {
        return ThemePaletteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_theme_item, parent, false),
            action
        )
    }

    override fun onBindViewHolder(holder: ThemePaletteViewHolder, position: Int) {
        holder.bind(themes[position])
    }

    override fun getItemCount(): Int = themes.size

    inner class ThemePaletteViewHolder(
        itemView: View,
        action: (Theme) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        var theme: Theme? = null

        init {
            itemView.setOnClickListener {
                theme?.let {
                    action(it)
                }
            }
        }

        fun bind(theme: Theme) {
            this.theme = theme
            itemView.context.run {
                Glide.with(this)
                    .load(ColorDrawable(ContextCompat.getColor(this, theme.displayColor)))
                    .circleCrop()
                    .into(itemView.ivThemeDisplay)
            }
        }
    }
}
