{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };

  outputs = {nixpkgs, ...}: let
    system = "x86_64-linux";
    pkgs = nixpkgs.legacyPackages.${system};
  in {
    formatter.x86_64-linux.default = pkgs.alejandra;
    devShells.x86_64-linux.default = pkgs.mkShellNoCC {
      packages = [
        pkgs.xspim
      ];
    };
  };
}
