package com.example.core_ui.base

class TimeTravelCapsule<State : Reducer.ViewState>(
    private val onStateSelected: (State) -> Unit
) : TimeCapsule<State> {

    private val states = mutableListOf<State>()

    override fun addState(state: State) {
        states.add(state)
    }

    override fun selectState(position: Int) {
        onStateSelected(states[position])
    }

    override fun getStates(): List<State> {
        return states
    }
}