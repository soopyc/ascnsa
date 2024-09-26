{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };

  outputs = {nixpkgs, ...}: let
    system = "x86_64-linux";
    pkgs = nixpkgs.legacyPackages.${system};
  in {
    formatter.${system} = pkgs.alejandra;
    devShells.${system} = {
      default = pkgs.mkShellNoCC {
        packages = with pkgs; [
          xspim
        ];
      };

      cross = let
        cross = pkgs.pkgsCross.mips-linux-gnu;
      in
        cross.mkShell {
          nativeBuildInputs = with cross; [
            pkg-config
            make
          ];
        };
    };
  };
}
