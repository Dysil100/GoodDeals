package duo.cmr.dysha.boundedContexts.generalhelpers.generalresearch;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchResult {
    private Object object;
    private int matchCount;
}
