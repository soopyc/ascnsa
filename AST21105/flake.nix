{
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable-small";
  };

  outputs = inputs: let
    lib = inputs.nixpkgs.lib;
    systems = ["x86_64-linux" "aarch64-linux"];
    forAllSystems = fn: lib.genAttrs systems (system: fn {inherit system; pkgs = inputs.nixpkgs.legacyPackages.${system};});
  in {
    devShells = forAllSystems ({pkgs, ...}: {default = pkgs.mkShell {
      packages = with pkgs; [clang];

    };});
  };
}
