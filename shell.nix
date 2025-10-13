let
	pkgs = import <nixpkgs> {
		config = {};
		overlays = [];
	};
in
	pkgs.mkShell {
		packages = with pkgs; [
			pandoc

			(hiPrio llvmPackages_21.clang-tools)
			llvmPackages_21.clang
		];
	}
