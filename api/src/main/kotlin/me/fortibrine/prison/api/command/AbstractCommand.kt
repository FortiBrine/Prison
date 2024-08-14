package me.fortibrine.prison.api.command

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

abstract class AbstractCommand (
    protected val command: String,
    protected val usage: String?,
    protected val description: String?,
    protected val permissionMessage: String?,
    protected val aliases: List<String>?
): CommandExecutor, TabCompleter {

    constructor(command: String) :
            this(command, null, null, null, null)

    constructor(command: String, usage: String?) :
            this(command, usage, null, null, null)

    constructor(command: String, usage: String?, description: String?) :
            this(command, usage, description, null, null)

    constructor(command: String, usage: String?, description: String?, permissionMessage: String?) :
            this(command, usage, description, permissionMessage, null)

    constructor(command: String, usage: String?, description: String?, aliases: List<String>?) :
            this(command, usage, description, null, aliases)

    init {
        register()
    }

    abstract override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean
    override fun onTabComplete(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }

    fun register() {
        val cmd = ReflectCommand(this.command)

        if (this.aliases != null) cmd.aliases = this.aliases
        if (this.description != null) cmd.description = this.description
        if (this.usage != null) cmd.usage = this.usage
        if (this.permissionMessage != null) cmd.permissionMessage = this.permissionMessage

        getCommandMap().register("", cmd)
        cmd.executor = this
    }

    fun getCommandMap(): CommandMap {
        val field = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
        field.isAccessible = true
        val commandMap = field.get(Bukkit.getServer()) as CommandMap
        return commandMap
    }

    private class ReflectCommand (
        command: String,
    ): Command(command) {

        public var executor: AbstractCommand? = null

        override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
            if (executor != null) {
                return executor!!.onCommand(sender, this, commandLabel, args)
            }

            return false
        }

        override fun tabComplete(sender: CommandSender, alais: String, args: Array<out String>): List<String> {
            if (executor != null) {
                return executor!!.onTabComplete(sender, this, alais, args)
            }

            return emptyList()
        }

    }

}