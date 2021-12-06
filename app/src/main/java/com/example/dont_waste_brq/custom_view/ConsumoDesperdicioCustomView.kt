package com.example.dont_waste_brq.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dont_waste_brq.R
import com.example.dont_waste_brq.activity.enum.EstadoEnum
import com.example.dont_waste_brq.databinding.ViewConsumoDesperdicioBinding

class ConsumoDesperdicioCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewConsumoDesperdicioBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var state : EstadoEnum = EstadoEnum.NEUTRO

    var estado: SetEstadoInterface? = null

    init {
        refreshState()
        setListners()
    }

    fun setListners() {
        binding.btnConsumo.setOnClickListener {
            if (state == EstadoEnum.NEUTRO) {
                setState(EstadoEnum.CONSUMIDO)
            } else if (state == EstadoEnum.DESPERDICIO){
                setState(EstadoEnum.NEUTRO)
            }
        }
        binding.btnDesperdicio.setOnClickListener {
            if (state == EstadoEnum.NEUTRO) {
                setState(EstadoEnum.DESPERDICIO)
            } else if (state == EstadoEnum.CONSUMIDO){
                setState(EstadoEnum.NEUTRO)
            }
        }
    }

    fun refreshState() {
        when (state) {
            EstadoEnum.CONSUMIDO -> {
                binding.chipBackground.setChipBackgroundColorResource(R.color.colorGreen)
                binding.chipConsumo.visibility = View.VISIBLE
                binding.chipNeutro.visibility = View.GONE
                binding.chipDesperdicio.visibility = View.GONE
            }
            EstadoEnum.NEUTRO -> {
                binding.chipBackground.setChipBackgroundColorResource(R.color.colorGreenPrimary)
                binding.chipConsumo.visibility = View.GONE
                binding.chipNeutro.visibility = View.VISIBLE
                binding.chipDesperdicio.visibility = View.GONE
            }
            EstadoEnum.DESPERDICIO -> {
                binding.chipBackground.setChipBackgroundColorResource(R.color.red)
                binding.chipConsumo.visibility = View.GONE
                binding.chipNeutro.visibility = View.GONE
                binding.chipDesperdicio.visibility = View.VISIBLE
            }
        }
    }

    fun setState(state: EstadoEnum) {
        this.state = state
        estado?.setEstado(state)
        refreshState()
    }

}