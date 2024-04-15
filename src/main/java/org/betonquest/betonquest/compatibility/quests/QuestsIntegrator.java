package org.betonquest.betonquest.compatibility.quests;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import me.pikamug.quests.Quests;
import org.betonquest.betonquest.BetonQuest;
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory;
import org.betonquest.betonquest.compatibility.Integrator;
import org.bukkit.Bukkit;

@SuppressWarnings("PMD.CommentRequired")
public class QuestsIntegrator implements Integrator {
    @SuppressWarnings("NotNullFieldNotInitialized")
    private static Quests questsInstance;

    private final BetonQuest plugin;

    public QuestsIntegrator() {
        plugin = BetonQuest.getInstance();
    }

    public static Quests getQuestsInstance() {
        return questsInstance;
    }

    @Override
    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
    public void hook() {
        questsInstance = (Quests) Bukkit.getPluginManager().getPlugin("Quests");
        plugin.registerConditions("quest", QuestsCondition.class);
        plugin.registerEvents("quest", QuestsEvent.class);
        final BetonQuestLoggerFactory loggerFactory = BetonQuest.getInstance().getLoggerFactory();
        questsInstance.getCustomRewards().add(new EventReward(loggerFactory.create(EventReward.class)));
        questsInstance.getCustomRequirements().add(new ConditionRequirement(loggerFactory.create(ConditionRequirement.class)));
    }

    @Override
    public void reload() {
        // Empty
    }

    @Override
    public void close() {
        // Empty
    }

}
