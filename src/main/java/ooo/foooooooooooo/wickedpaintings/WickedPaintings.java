package ooo.foooooooooooo.wickedpaintings;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ooo.foooooooooooo.wickedpaintings.client.screen.WickedGuiDescription;
import ooo.foooooooooooo.wickedpaintings.config.ModConfig;
import ooo.foooooooooooo.wickedpaintings.entity.ModEntityTypes;
import ooo.foooooooooooo.wickedpaintings.item.ModItems;
import ooo.foooooooooooo.wickedpaintings.network.ModNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WickedPaintings implements ModInitializer {
  public static final String MOD_ID = "wicked_paintings";
  public static ScreenHandlerType<WickedGuiDescription> WICKED_SCREEN_HANDLER_TYPE;
  public static final Identifier GENERAL_GROUP_ID = new Identifier(MOD_ID, "general");
  public static final ItemGroup GENERAL_ITEM_GROUP = FabricItemGroup
    .builder(GENERAL_GROUP_ID)
    .displayName(Text.translatable("itemGroup.wicked_paintings.general"))
    .icon(() -> new ItemStack(ModItems.WICKED_PAINTING))
    .build();

  public static final Logger LOGGERS = LoggerFactory.getLogger(WickedPaintings.class);

  @Override
  public void onInitialize() {
    Log.info(LogCategory.LOG, "Loading Wicked Paintings");
    AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);

    WICKED_SCREEN_HANDLER_TYPE = new ExtendedScreenHandlerType<>((syncId, inventory, buf) -> {
      var equipmentSlot = buf.readEnumConstant(EquipmentSlot.class);
      var handStack = StackReference.of(inventory.player, equipmentSlot);
      return new WickedGuiDescription(syncId, inventory, handStack);
    });

    Registry.register(Registries.SCREEN_HANDLER, new Identifier(MOD_ID, "wicked_gui"), WICKED_SCREEN_HANDLER_TYPE);

    ModEntityTypes.registerEntityTypes();
    ModItems.registerItems();
    ModNetworking.registerServerBoundPackets();
  }
}
