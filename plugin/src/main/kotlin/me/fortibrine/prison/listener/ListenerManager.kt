package me.fortibrine.prison.listener

import me.fortibrine.prison.settings.BreakListener
import me.fortibrine.prison.settings.DropListener
import me.fortibrine.prison.settings.JoinListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListenerManager @Inject constructor(
    private val firstJoinListener: FirstJoinListener,

    private val joinListener: JoinListener,
    private val dropListener: DropListener,
    private val breakListener: BreakListener,
)
