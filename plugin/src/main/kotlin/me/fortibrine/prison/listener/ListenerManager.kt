package me.fortibrine.prison.listener

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListenerManager @Inject constructor(
    private val firstJoinListener: FirstJoinListener
)
