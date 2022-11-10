package me.megabot.paranoia.config;

import org.bukkit.entity.EntityType;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ParanoiaConfig {
    private static int percentageChance = 10;
    private static boolean isPluginEnabled = true;
    private static Set<UUID> disabledPlayers = new HashSet<>();

    private static List<EntityType> entities =
            Arrays.asList(
                    EntityType.AXOLOTL,
                    EntityType.BAT,
                    EntityType.BEE,
                    EntityType.BLAZE,
                    EntityType.CHICKEN,
                    EntityType.COD,
                    EntityType.COW,
                    EntityType.CREEPER,
                    EntityType.DOLPHIN,
                    EntityType.DONKEY,
                    EntityType.DROWNED,
                    EntityType.ENDERMAN,
                    EntityType.EVOKER,
                    EntityType.FOX,
                    EntityType.FROG,
                    EntityType.GHAST,
                    EntityType.GOAT,
                    EntityType.GLOW_SQUID,
                    EntityType.GUARDIAN,
                    EntityType.HOGLIN,
                    EntityType.HORSE,
                    EntityType.HUSK,
                    EntityType.ILLUSIONER,
                    EntityType.IRON_GOLEM,
                    EntityType.LLAMA,
                    EntityType.MAGMA_CUBE,
                    EntityType.MULE,
                    EntityType.MUSHROOM_COW,
                    EntityType.OCELOT,
                    EntityType.PANDA,
                    EntityType.PHANTOM,
                    EntityType.PIG,
                    EntityType.PIGLIN,
                    EntityType.PIGLIN_BRUTE,
                    EntityType.PILLAGER,
                    EntityType.POLAR_BEAR,
                    EntityType.PUFFERFISH,
                    EntityType.RABBIT,
                    EntityType.RAVAGER,
                    EntityType.SALMON,
                    EntityType.SHEEP,
                    EntityType.SHULKER,
                    EntityType.SILVERFISH,
                    EntityType.SKELETON,
                    EntityType.SKELETON_HORSE,
                    EntityType.SLIME,
                    EntityType.SNOWMAN,
                    EntityType.SPIDER,
                    EntityType.SQUID,
                    EntityType.STRAY,
                    EntityType.STRIDER,
                    EntityType.TADPOLE,
                    EntityType.TROPICAL_FISH,
                    EntityType.TURTLE,
                    EntityType.VEX,
                    EntityType.VILLAGER,
                    EntityType.WARDEN,
                    EntityType.WITCH,
                    EntityType.WITHER_SKELETON,
                    EntityType.ZOGLIN,
                    EntityType.ZOMBIE,
                    EntityType.ZOMBIE_VILLAGER,
                    EntityType.ZOMBIFIED_PIGLIN);

    public static void setPercentageChance(int percentage) {
        ParanoiaConfig.percentageChance = percentage;
    }

    public static int getPercentageChance() {
        return ParanoiaConfig.percentageChance;
    }

    public static void setIsPluginEnabled(boolean state) {
        ParanoiaConfig.isPluginEnabled = state;
    }

    public static boolean isPluginEnabled() {
        return ParanoiaConfig.isPluginEnabled;
    }

    public static boolean disablePlayerParanoia(UUID playerId) {
        return ParanoiaConfig.disabledPlayers.add(playerId);
    }

    public static boolean enablePlayerParanoia(UUID playerId) {
        return ParanoiaConfig.disabledPlayers.remove(playerId);
    }

    public static boolean isPlayerParanoid(UUID playerId) {
        return !ParanoiaConfig.disabledPlayers.contains(playerId);
    }

    public static EntityType getRandomEntity() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, ParanoiaConfig.entities.size());
        return ParanoiaConfig.entities.get(randomNum);
    }
}
