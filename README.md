# Dummy Hex Casting Addon

Or HexDummy for short! This is an up-to-date bare-bones template for starting a multiloader Hex Casting addon on
1.19.2 with Architectury. Includes all necessary dependencies on both Forge and Fabric loaders, plus some demo
bits. There is nothing specifically for Quilt here because Fabric builds implicitly support Quilt.

It is an amalgamation of the [HexCastingEmptyAddon](https://github.com/Talia-12/EmptyHexCastingAddon),
the forge-fabric-mixin 1.19.2 template
from [Architectury templates](https://github.com/architectury/architectury-templates), a few helpful bits from
[Hex Gloop](https://github.com/SamsTheNerd/HexGloop)'s setup, and some custom improvements thrown in.

## How to get started?

1. Install Python 3.11+, then follow [these instructions](https://pypa.github.io/pipx/#install-pipx) to install pipx.
   * pipx is basically like Node's `npx` but for Python - it's a tool to help you install and run end-user applications written in Python.
2. Create, clone, and enter a new, completely empty GitHub repo (**do not** fork/clone/copy this repo directly).
3. From the repo root, run these commands to copy the template, then follow the prompts to set it up:
   ```sh
   pipx install copier

   # main addon
   pipx run copier copy gh:FallingColors/hexdummy .
   
   # hexdoc web book
   # note: unless otherwise specified, when the prompts here refer to "package", it means the Python hexdoc addon package being created
   pipx run copier copy gh:hexdoc-dev/hexdoc-hexcasting-template . --answers-file .hexdoc-template-inputs.yml --skip .gitignore --defaults
   ```
4. Launch the game client with `./gradlew fabric:runClient` (Unix) or `.\gradlew.bat fabric:runClient` (Windows) to make sure it works.
5. Finish setting up the web book:
   1. Follow the setup steps in `doc/README.md`.
   2. Try running `hexdoc serve` to make sure the book works.
   3. Follow the [hexdoc-hexcasting-template setup steps](https://github.com/hexdoc-dev/hexdoc-hexcasting-template#setting-up-pages), starting at "Setting up Pages".
6. Add `modrinthApiToken` and `curseforgeApiToken` values to your user-specific gradle properties
   in [Gradle User Home](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home).
   You can leave them empty for now.
7. Check out the [Architectury wiki](https://docs.architectury.dev/start) if you haven't yet. If you can't find
   something there, try searching on their Discord.
8. After checking out how demo patterns are registered, replace them with your own! You can look into Hex Casting
   sources to see real implementations. If you're new to this,
   here's a challenge: try making a single-pattern equivalent of the raycast mantra.
9. (Optional) Set up the publishMods tasks to simplify publishing updates: add necessary API keys to your
   user-specific properties, add necessary project ids to root `gradle.properties`, and uncomment
   curseforge / modrinth / both segments in the publishMods task in `build.gradle` files from `forge` and 
   `fabric` directories.

## Common problems

- IntelliJ IDEA shows false-positive errors about not being to access something in Kotlin files, but code compiles and
  runs fine.
    - Delete the .idea folder and re-open the project.

## Why did I make this?

To put it simply, I have a WIP addon of my own, and was looking for a sustainable long-term solution for supporting all the major loaders (Forge,
Fabric, Quilt). I had a hard time figuring it out at first, then got carried away, and the end result is something
that, I think, might be useful to people other than me.

Some conveniences I have managed to get to work so that you don't have to:

- Patchouli additions showing up in dev environment (there were problems with how patchouli reads its data).
- Slightly improved docgen system based on the one from HexCastingEmptyAddon, with a gradle task and fixes to a few
  issues:
    - Patterns category becoming a spoiler if there are spell patterns but no regular patterns.
    - Per-world patterns not actually parsing as per-world (was a regex issue).

There are other templates, like the aforementioned Talia-12's EmptyHexCastingAddon, but this one is a bit more
light, beginner-friendly, and up-to-date, in my opinion. However, do check out Talia's template if you would like
to see how to add configs and / or see how the Xplat multiloader layout works, which is what Hex Casting
itself and some other addons use.

## Contributing

Important: Remember to always add a new Git version tag when updating the template, or Copier won't pick up the new changes. See [hexdoc-hexcasting-template](https://github.com/hexdoc-dev/hexdoc-hexcasting-template) for a good example of this.

To test the template and web book:
- Follow the above instructions to install Python and pipx.
- Install Nox: `pipx install nox`
- Run the Nox session: `nox -s book`

If you get an error like `Unsupported class file major version 65` when running the tests, Gradle is trying to use the wrong version of Java (this can happen if it's too old *or* too new). As a workaround, you can set the `JAVA_HOME` environment variable. Nox will also read a file in the root of this repo called `.java_home.env` containing the path to use if it exists.

## Acknowledgements

- [Petrak@](https://github.com/gamma-delta) and other Hex Casting devs, for making Hex Casting in general.
- [Talia-12](https://github.com/Talia-12), for making the stripped-down addon template which helped me get started
  with this one.
- [SamsTheNerd](https://github.com/SamsTheNerd), author of the Hex Gloop addon also powered by Architectury: its
  setup code helped me resolve an issue or two with Architectury here.
- [Architectury](https://github.com/architectury) devs and their Discord server.
- [object-Object](https://github.com/object-Object) and [Cypher](https://github.com/Cypher121), for some
  helpful pointers after initial template release.
