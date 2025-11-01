let
	pkgs = import (fetchTarball "https://nixpkgs.dev/channel/nixos-unstable") {
		config = {};
		overlays = [];
	};
in
	pkgs.mkShellNoCC {
		env.CC = "clang";
		env.CXX = "clang++";
		packages = with pkgs; [
			pandoc

			(hiPrio llvmPackages_21.clang-tools)
			llvmPackages_21.clang
			cmake
			ninja
		];
	}
