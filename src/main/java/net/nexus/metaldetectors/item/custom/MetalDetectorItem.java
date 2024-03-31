package net.nexus.metaldetectors.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.nexus.metaldetectors.MetalDetectors;

public class MetalDetectorItem extends Item {

    private final int yRange;
    private final boolean precise;

    public MetalDetectorItem(Settings settings, int yRange, boolean precise) {
        super(settings);
        this.yRange = yRange;
        this.precise = precise;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()) {
            BlockPos posClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;
            for(int i = 0; i <= posClicked.getY() + this.yRange; i++) {
                BlockState state = context.getWorld().getBlockState(posClicked.down(i));
                if(isValuableBlock(state)) {
                    int distance = player.getBlockY() - posClicked.down(i).getY();
                    float pitch = (float) Math.max(0.1, Math.min(1.75, (distance/100f))) * 2f;
                    context.getWorld().playSound(null, posClicked, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(), SoundCategory.BLOCKS, 1f, pitch);
                    outputValuableCoords(posClicked.down(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if(!foundBlock) {
                player.sendMessage(Text.literal("No valuables found!"));
            }
        }

        context.getStack().damage(1, context.getPlayer(), playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    private void outputValuableCoords(BlockPos blockPos, PlayerEntity player, Block block) {
        if (precise) {
            player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " + blockPos.getY()), false);
        }else{
            player.sendMessage(Text.literal("Found valuables!"));
        }
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DIAMOND_ORE);
    }
}
