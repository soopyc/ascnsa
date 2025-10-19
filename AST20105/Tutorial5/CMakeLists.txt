add_executable(Tutorial5)
add_library(Tutorial5Lib)

target_sources(Tutorial5Lib
	PRIVATE
		MySinglyList.cpp
		SinglyList.cpp

	PUBLIC
		FILE_SET tutorial5Headers
		TYPE HEADERS
		FILES
			Node.hh
			SinglyList.hh
			MySinglyList.hh
)

target_sources(Tutorial5
	PRIVATE
		main.cpp
)

target_link_libraries(Tutorial5
	PRIVATE
		Tutorial5Lib
)
