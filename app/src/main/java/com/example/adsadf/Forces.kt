package com.example.adsadf

data class Forces(val name: String, val id: String)

data class SpecialForce(val url: String, val description: String,
                        val name: String, val telephone: String)

data class Crime(val category: String, val location_type: String,
                             val location: Location, val context: String,
                             val outcome_status: OutcomeStatus, val location_subtype: String,
                             val month: String)

data class Location(val latitude:String, val street: Street, val longitude: String)

data class Street(val id: String, val name: String)

data class OutcomeStatus(val category: String, val date: String)
