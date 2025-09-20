#!/bin/bash

# --- CONFIGURATION ---
# This script should be in the same directory as caching-proxy.sh.sh.sh
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
SOURCE_SCRIPT_NAME="caching-proxy.sh"
TARGET_COMMAND_NAME="caching-proxy"
INSTALL_DIR="$HOME/.local/bin"

# --- ANSI COLOR CODES ---
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

echo -e "${CYAN}--- Caching Proxy CLI Installer ---${NC}"

# --- SANITY CHECK ---
# Ensure the source script exists in the current directory
SOURCE_SCRIPT_PATH="$SCRIPT_DIR/$SOURCE_SCRIPT_NAME"
if [ ! -f "$SOURCE_SCRIPT_PATH" ]; then
    echo -e "❌ ${YELLOW}Error: Script '$SOURCE_SCRIPT_NAME' not found in this directory.${NC}"
    echo "Please run this installer from the same directory as your proxy script."
    exit 1
fi

TARGET_SCRIPT_PATH="$SCRIPT_DIR/$TARGET_COMMAND_NAME"

# --- STEP 1: PREPARE THE SCRIPT ---
echo -e "\n1. Preparing the script..."

# Rename if it exists with the .sh extension
if [ -f "$SOURCE_SCRIPT_PATH" ]; then
    mv "$SOURCE_SCRIPT_PATH" "$TARGET_SCRIPT_PATH"
    echo "   - Renamed '$SOURCE_SCRIPT_NAME' to '$TARGET_COMMAND_NAME'"
fi

# Make it executable
chmod +x "$TARGET_SCRIPT_PATH"
echo "   - Made '$TARGET_COMMAND_NAME' executable"

# --- STEP 2: SETUP THE SYMLINK ---
echo -e "\n2. Setting up the symbolic link..."

# Ensure the installation directory exists
mkdir -p "$INSTALL_DIR"
echo "   - Ensured installation directory exists: $INSTALL_DIR"

# Create or update the symbolic link
# The -f flag forces an overwrite if a broken link already exists
ln -sf "$TARGET_SCRIPT_PATH" "$INSTALL_DIR/$TARGET_COMMAND_NAME"
echo "   - Created symlink in $INSTALL_DIR"

# --- STEP 3: CONFIGURE THE SHELL PATH ---
echo -e "\n3. Checking shell configuration..."

RC_FILE=""
SHELL_NAME=$(basename "$SHELL")

if [[ "$SHELL_NAME" == "zsh" ]]; then
    RC_FILE="$HOME/.zshrc"
elif [[ "$SHELL_NAME" == "bash" ]]; then
    RC_FILE="$HOME/.bashrc"
fi

if [[ -z "$RC_FILE" ]]; then
    echo -e "   - ${YELLOW}Could not detect your shell config file (.bashrc or .zshrc).${NC}"
    echo -e "   - ${YELLOW}Please add the following line to your shell's startup file manually:${NC}"
    echo -e "     export PATH=\"\$HOME/.local/bin:\$PATH\""
else
    # Check if the path is already in the config file
    if ! grep -q "export PATH=\"\$HOME/.local/bin:\$PATH\"" "$RC_FILE"; then
        echo "   - Adding '$INSTALL_DIR' to PATH in $RC_FILE"
        echo '' >> "$RC_FILE"
        echo '# Add local bin to PATH for custom scripts' >> "$RC_FILE"
        echo 'export PATH="$HOME/.local/bin:$PATH"' >> "$RC_FILE"
        ADDED_TO_RC=true
    else
        echo "   - PATH is already correctly configured in $RC_FILE"
        ADDED_TO_RC=false
    fi
fi


# --- FINAL INSTRUCTIONS ---
echo -e "\n${GREEN}✅ Installation Complete!${NC}"
echo -e "The '${CYAN}caching-proxy${NC}' command is now installed."

if [[ "$ADDED_TO_RC" = true ]]; then
    echo -e "\n${YELLOW}To finish, please either open a NEW terminal window or run this command:${NC}"
    echo -e "  source $RC_FILE"
else
    echo -e "\nYou can now use the command from any directory."
fi

echo -e "\nTry it out:"
echo -e "  ${CYAN}caching-proxy status${NC}"