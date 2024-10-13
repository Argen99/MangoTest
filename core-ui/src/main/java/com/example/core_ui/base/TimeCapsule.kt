package com.example.core_ui.base

interface TimeCapsule<State : Reducer.ViewState> {
    fun addState(state: State)
    fun selectState(position: Int)
    fun getStates(): List<State>
}