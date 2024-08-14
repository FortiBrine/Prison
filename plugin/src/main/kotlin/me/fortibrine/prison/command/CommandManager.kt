package me.fortibrine.prison.command

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandManager @Inject constructor(
    private val testCommand: TestCommand
)
