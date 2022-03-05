package net.blf02.vrapi.debug;

import net.blf02.vrapi.VRAPIMod;
import net.blf02.vrapi.api.data.IVRPlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber
public class DebugSubscriber {

    private static final boolean SPAM_CONSOLE_WITH_VR_DATA = true; // Spam the console when debugging

    public static void initDebug() {

    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) return;
        PlayerEntity player = event.player;
        IVRPlayer vrPlayer = DebugPlugin.vrAPI.getVRPlayer(player);
        if (vrPlayer == null) return;
        if (SPAM_CONSOLE_WITH_VR_DATA) VRAPIMod.LOGGER.log(Level.INFO, vrPlayer.getController0().getPosition());
        for (int i = 0; i < 20; i++) {
            Vector3d toCheckHMD = vrPlayer.getHMD().getPosition().add(vrPlayer.getHMD().getLookVec().multiply(i, i, i));
            Vector3d toCheck0 = vrPlayer.getController0().getPosition().add(vrPlayer.getController0().getLookVec().multiply(i, i, i));
            Vector3d toCheck1 = vrPlayer.getController1().getPosition().add(vrPlayer.getController1().getLookVec().multiply(i, i, i));

            if (player.level.isClientSide) {
                player.level.addParticle(ParticleTypes.SMOKE, toCheckHMD.x, toCheckHMD.y, toCheckHMD.z, 0, 0, 0);
                player.level.addParticle(ParticleTypes.ANGRY_VILLAGER, toCheck0.x, toCheck0.y, toCheck0.z, 0, 0, 0);
                player.level.addParticle(ParticleTypes.DRIPPING_LAVA, toCheck1.x, toCheck1.y, toCheck1.z, 0, 0, 0);
            } else {
                player.level.removeBlock(new BlockPos(toCheckHMD), true);
                player.level.removeBlock(new BlockPos(toCheck0), true);
                player.level.removeBlock(new BlockPos(toCheck1), true);
            }
        }
    }
}