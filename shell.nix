let
	pkgs = import <nixpkgs> {
		config = {};
		overlays = [];
	};
in
	pkgs.mkShell {
		packages = with pkgs; [
			llvmPackages_19.clang
			llvmPackages_19.clang-tools
		];
	}
