package ooo.foooooooooooo.wickedpaintings.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ooo.foooooooooooo.wickedpaintings.WickedPaintings;

public class ModItems {
  public static final Item WICKED_PAINTING = new WickedPaintingItem(defaultSettings());

  public static Item.Settings defaultSettings() {
    return new Item.Settings();
  }


  public static void registerItems() {
    register(WICKED_PAINTING, "wicked_painting");
  }

  public static void register(Item item, String name) {
    Registry.register(Registries.ITEM, new Identifier(WickedPaintings.MOD_ID, name), item);

    ItemGroupEvents.modifyEntriesEvent(WickedPaintings.GENERAL_ITEM_GROUP).register(entries -> entries.add(item));
  }
}
