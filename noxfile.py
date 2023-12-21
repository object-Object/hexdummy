import os
import platform
import shutil
import stat
from pathlib import Path
from typing import Any

import nox

CI_GIT_CONFIGS = {
    "user.name": "GitHub Actions",
    "user.email": "41898282+github-actions[bot]@users.noreply.github.com",
    "init.defaultBranch": "main",
}

MAPPINGS_NAMES = [
    "mojmap",
    "yarn",
]

nox.options.reuse_existing_virtualenvs = True
nox.options.stop_on_first_error = True


def parametrize_output_dir():
    return nox.parametrize(
        "output_dir",
        [Path(".ctt") / name for name in MAPPINGS_NAMES],
        ids=MAPPINGS_NAMES,
    )


# sessions


@nox.session
def ctt(session: nox.Session):
    session.install("copier-template-tester")

    ctt_dir = Path(".ctt")
    if ctt_dir.is_dir():
        session.log(f"Removing directory: {ctt_dir}")
        shutil.rmtree(ctt_dir, onerror=on_rm_error)

    session.run("ctt", silent=not is_ci())


@nox.session
@parametrize_output_dir()
def setup(session: nox.Session, output_dir: Path):
    session.chdir(output_dir)

    session.install("copier")

    if is_ci():
        for key, value in CI_GIT_CONFIGS.items():
            session.run("git", "config", "--global", key, value, external=True)

    session.run("git", "init", external=True)

    session.run(
        "git",
        "commit",
        "--allow-empty",
        "-m",
        "Initial commit",
        external=True,
    )

    session.run(
        "copier",
        "copy",
        "gh:hexdoc-dev/hexdoc-hexcasting-template",
        ".",
        "--answers-file",
        ".hexdoc-template-inputs.yml",
        "--skip",
        ".gitignore",
        "--defaults",
    )


@nox.session(python=False)
@parametrize_output_dir()
def gradle_build(session: nox.Session, output_dir: Path):
    session.chdir(output_dir)

    session.run(gradle(), "build", external=True)


@nox.session
@parametrize_output_dir()
def hexdoc(session: nox.Session, output_dir: Path):
    session.chdir(output_dir)

    session.install(".")
    session.run("pip", "freeze")

    session.run("hexdoc", "build")
    session.run("hexdoc", "merge")


# helpers


def on_rm_error(func: Any, path: str, exc_info: Any):
    # from: https://stackoverflow.com/questions/4829043/how-to-remove-read-only-attrib-directory-with-python-in-windows
    path_ = Path(path)
    path_.chmod(stat.S_IWRITE)
    path_.unlink()


def is_ci():
    return os.getenv("CI") == "true"


def gradle():
    match platform.system():
        case "Windows":
            return ".\\gradlew.bat"
        case _:
            return "./gradlew"
