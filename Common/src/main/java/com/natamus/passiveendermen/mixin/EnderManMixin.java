package com.natamus.passiveendermen.mixin;

import com.natamus.passiveendermen.config.ConfigHandler;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnderMan.class, priority = 1001)
public class EnderManMixin {
	@Inject(method = "teleport(DDD)Z", at = @At(value = "HEAD"), cancellable = true)
	private void teleport(double p_32544_, double p_32545_, double p_32546_, CallbackInfoReturnable<Boolean> cir) {
		if (ConfigHandler.preventEndermenFromTeleporting) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "isLookingAtMe(Lnet/minecraft/world/entity/player/Player;)Z", at = @At(value = "HEAD"), cancellable = true)
	void isLookingAtMe(Player player, CallbackInfoReturnable<Boolean> cir) {
		if (ConfigHandler.preventEndermenFromAttackingFirst) {
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "getCarriedBlock()Lnet/minecraft/world/level/block/state/BlockState;", at = @At(value = "HEAD"), cancellable = true)
	public void getCarriedBlock(CallbackInfoReturnable<BlockState> cir) {
		if (ConfigHandler.preventEndermenFromGriefing) {
			cir.setReturnValue(null);
		}
	}
}
