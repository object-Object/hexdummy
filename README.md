# HexDummy v2

This branch contains the heavily work-in-progress HexDummy rewrite.

## Usage

1. Install Python 3.11+, then follow [these instructions](https://pypa.github.io/pipx/#install-pipx) to install pipx.
2. Create, clone, and enter a new, completely empty GitHub repo (**do not** fork/clone/copy this repo directly).
3. From the repo root, run these commands to copy the template, then follow the prompts to set it up:
   ```sh
   # main addon
   pipx run copier copy gh:FallingColors/hexdummy . --vcs-ref v2

   # hexdoc web book (TODO: remove)
   # note: unless otherwise specified, when the prompts here refer to "package", it means the Python hexdoc addon package being created
   pipx run copier copy gh:hexdoc-dev/hexdoc-hexcasting-template . --answers-file .hexdoc-template-inputs.yml --skip .gitignore --defaults
   ```
